import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const BlogListPage = () => {
  const [posts, setPosts] = useState([]);
  const [newPost, setNewPost] = useState({ title: '', content: '' });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/post/all');
        setPosts(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching posts:', error);
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  const handleCreatePost = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/post/create', newPost);
      setPosts([response.data, ...posts]);
      setNewPost({ title: '', content: '' });
    } catch (error) {
      console.error('Error creating post:', error);
    }
  };

  const truncateContent = (content) => {
    const words = content.split(' ');
    if (words.length > 20) {
      return words.slice(0, 20).join(' ') + '...';
    }
    return content;
  };

  if (loading) return <div>Загрузка...</div>;

  return (
    <div style={{ maxWidth: '800px', margin: '0 auto', padding: '20px' }}>
      <h1>Блоги</h1>
      
      <div style={{ marginBottom: '30px' }}>
        <h2>Создать новый пост</h2>
        <form onSubmit={handleCreatePost} style={{ marginBottom: '20px' }}>
          <div style={{ marginBottom: '10px' }}>
            <input
              type="text"
              placeholder="Заголовок"
              value={newPost.title}
              onChange={(e) => setNewPost({ ...newPost, title: e.target.value })}
              required
              style={{ width: '100%', padding: '8px' }}
            />
          </div>
          <div style={{ marginBottom: '10px' }}>
            <textarea
              placeholder="Содержание"
              value={newPost.content}
              onChange={(e) => setNewPost({ ...newPost, content: e.target.value })}
              required
              style={{ width: '100%', padding: '8px', minHeight: '100px' }}
            />
          </div>
          <button type="submit" style={{ padding: '8px 15px', background: '#28a745', color: 'white', border: 'none' }}>
            Опубликовать
          </button>
        </form>
      </div>
      
      <div>
        {posts.map(post => (
          <div key={post.id} style={{ marginBottom: '20px', padding: '15px', border: '1px solid #ddd', borderRadius: '5px' }}>
            <h3>
              <Link to={`/posts/${post.id}`} style={{ color: '#007bff', textDecoration: 'none' }}>
                {post.title}
              </Link>
            </h3>
            <p>{truncateContent(post.content)}</p>
            <small>Опубликовано: {new Date(post.createdAt).toLocaleDateString()}</small>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BlogListPage;