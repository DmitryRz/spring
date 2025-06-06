import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

const BlogPostPage = () => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [postResponse, commentsResponse] = await Promise.all([
          axios.get(`http://localhost:8080/api/post/${id}`),
          axios.get(`http://localhost:8080/api/comments/${id}`)
        ]);
        
        setPost(postResponse.data);
        setComments(commentsResponse.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  const handleAddComment = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/api/comments/${id}`,
        { content: newComment }
      );
      
      setComments([...comments, response.data]);
      setNewComment('');
    } catch (error) {
      console.error('Error adding comment:', error);
    }
  };

  if (loading) return <div>Загрузка...</div>;
  if (!post) return <div>Пост не найден</div>;

  return (
    <div style={{ maxWidth: '800px', margin: '0 auto', padding: '20px' }}>
      <Link to="/" style={{ display: 'inline-block', marginBottom: '20px', color: '#007bff' }}>
        ← Назад к списку постов
      </Link>
      
      <article style={{ marginBottom: '40px' }}>
        <h1>{post.title}</h1>
        <p style={{ whiteSpace: 'pre-line', marginBottom: '15px' }}>{post.content}</p>
        <small>
          Опубликовано: {new Date(post.createdAt).toLocaleDateString()} | 
          Обновлено: {new Date(post.updatedAt).toLocaleDateString()}
        </small>
      </article>
      
      <section>
        <h2>Комментарии ({comments.length})</h2>
        
        <form onSubmit={handleAddComment} style={{ marginBottom: '20px' }}>
          <textarea
            placeholder="Ваш комментарий..."
            value={newComment}
            onChange={(e) => setNewComment(e.target.value)}
            required
            style={{ width: '100%', padding: '8px', minHeight: '80px', marginBottom: '10px' }}
          />
          <button type="submit" style={{ padding: '8px 15px', background: '#28a745', color: 'white', border: 'none' }}>
            Отправить
          </button>
        </form>
        
        {comments.length === 0 ? (
          <p>Пока нет комментариев. Будьте первым!</p>
        ) : (
          <div>
            {comments.map(comment => (
              <div key={comment.id} style={{ marginBottom: '15px', padding: '15px', border: '1px solid #eee', borderRadius: '5px' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '5px' }}>
                  <strong>{comment.authorUsername}</strong>
                  <small>{new Date(comment.createdAt).toLocaleDateString()}</small>
                </div>
                <p>{comment.content}</p>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
};

export default BlogPostPage;