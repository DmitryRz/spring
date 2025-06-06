import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getAllPosts, createPost } from '../utils/api';

export default function PostsPage() {
  const [posts, setPosts] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [showForm, setShowForm] = useState(false);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    if (!token) {
      navigate('/');
      return;
    }
    
    const fetchPosts = async () => {
      try {
        const response = await getAllPosts();
        setPosts(response.data);
      } catch (err) {
        console.error(err);
      }
    };
    
    fetchPosts();
  }, [navigate, token]);

  const handleCreatePost = async (e) => {
    e.preventDefault();
    try {
      await createPost(title, content, token);
      const response = await getAllPosts();
      setPosts(response.data);
      setTitle('');
      setContent('');
      setShowForm(false);
    } catch (err) {
      console.error(err);
    }
  };

  const shortenContent = (text) => {
    const words = text.split(' ');
    return words.length > 20 ? words.slice(0, 20).join(' ') + '...' : text;
  };

  return (
    <div>
      <h1>All Posts</h1>
      
      {token && (
        <button onClick={() => setShowForm(!showForm)}>
          {showForm ? 'Cancel' : 'Create Post'}
        </button>
      )}
      
      {showForm && (
        <form onSubmit={handleCreatePost}>
          <div>
            <label>Title:</label>
            <input 
              type="text" 
              value={title} 
              onChange={(e) => setTitle(e.target.value)} 
              required 
            />
          </div>
          <div>
            <label>Content:</label>
            <textarea 
              value={content} 
              onChange={(e) => setContent(e.target.value)} 
              required 
            />
          </div>
          <button type="submit">Submit</button>
        </form>
      )}
      
      <div>
        {posts.map(post => (
          <div key={post.id} style={{ margin: '20px 0', border: '1px solid #ccc', padding: '10px' }}>
            <Link to={`/post/${post.id}`}>
              <h3>{post.title}</h3>
            </Link>
            <p>{shortenContent(post.content)}</p>
            <small>Posted by user {post.userId} on {new Date(post.createdAt).toLocaleString()}</small>
          </div>
        ))}
      </div>
    </div>
  );
}