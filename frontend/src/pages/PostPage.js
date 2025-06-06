import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { 
  getPost, 
  getComments, 
  addComment, 
  updatePost, 
  deletePost 
} from '../utils/api';

export default function PostPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const [commentContent, setCommentContent] = useState('');
  const [editMode, setEditMode] = useState(false);
  const [editTitle, setEditTitle] = useState('');
  const [editContent, setEditContent] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  
  const token = localStorage.getItem('token');
  const currentUser = localStorage.getItem('username');

  useEffect(() => {
    if (!token) {
      navigate('/');
      return;
    }

    const fetchData = async () => {
      try {
        const [postResponse, commentsResponse] = await Promise.all([
          getPost(id),
          getComments(id)
        ]);
        setPost(postResponse.data);
        setComments(commentsResponse.data);
        setEditTitle(postResponse.data.title);
        setEditContent(postResponse.data.content);
      } catch (err) {
        setError('Failed to fetch post data');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [id, navigate, token]);

  const handleAddComment = async (e) => {
    e.preventDefault();
    try {
      await addComment(id, commentContent, token);
      const response = await getComments(id);
      setComments(response.data);
      setCommentContent('');
    } catch (err) {
      setError('Failed to add comment');
    }
  };

  const handleUpdatePost = async (e) => {
    e.preventDefault();
    try {
      const response = await updatePost(id, editTitle, editContent, token);
      setPost(response.data);
      setEditMode(false);
    } catch (err) {
      setError('Failed to update post');
    }
  };

  const handleDeletePost = async () => {
    if (window.confirm('Are you sure you want to delete this post?')) {
      try {
        await deletePost(id, token);
        navigate('/posts');
      } catch (err) {
        setError('Failed to delete post');
      }
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;
  if (!post) return <div>Post not found</div>;

  const isAuthor = post.userId === currentUser;

  return (
    <div>
      {editMode ? (
        <form onSubmit={handleUpdatePost}>
          <div>
            <label>Title:</label>
            <input 
              type="text" 
              value={editTitle} 
              onChange={(e) => setEditTitle(e.target.value)} 
              required 
            />
          </div>
          <div>
            <label>Content:</label>
            <textarea 
              value={editContent} 
              onChange={(e) => setEditContent(e.target.value)} 
              required 
            />
          </div>
          <button type="submit">Save</button>
          <button type="button" onClick={() => setEditMode(false)}>Cancel</button>
        </form>
      ) : (
        <div>
          <h1>{post.title}</h1>
          <p>{post.content}</p>
          <small>
            Posted by user {post.userId} on {new Date(post.createdAt).toLocaleString()}
          </small>
          
          {isAuthor && (
            <div>
              <button onClick={() => setEditMode(true)}>Edit Post</button>
              <button onClick={handleDeletePost}>Delete Post</button>
            </div>
          )}
        </div>
      )}
      
      <h2>Comments</h2>
      <form onSubmit={handleAddComment}>
        <textarea 
          value={commentContent} 
          onChange={(e) => setCommentContent(e.target.value)} 
          placeholder="Write a comment..." 
          required 
        />
        <button type="submit">Add Comment</button>
      </form>
      
      <div>
        {comments.map(comment => (
          <div key={comment.id} style={{ margin: '10px 0', padding: '10px', border: '1px solid #eee' }}>
            <p>{comment.content}</p>
            <small>
              By {comment.authorUsername} on {new Date(comment.createdAt).toLocaleString()}
            </small>
          </div>
        ))}
      </div>
    </div>
  );
}