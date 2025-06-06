import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const register = async (username, email, password) => {
  return axios.post(`${API_URL}/auth/register`, { username, email, password });
};

export const login = async (username, password) => {
  return axios.post(`${API_URL}/auth/login`, { username, password });
};

export const getAllPosts = async () => {
  return axios.get(`${API_URL}/posts/all`);
};

export const getPost = async (id) => {
  return axios.get(`${API_URL}/post/${id}`);
};

export const createPost = async (title, content, token) => {
  return axios.post(`${API_URL}/post/create`, { title, content }, {
    headers: { Authorization: `Bearer ${token}` }
  });
};

export const updatePost = async (id, title, content, token) => {
  return axios.put(`${API_URL}/post/${id}`, { title, content }, {
    headers: { Authorization: `Bearer ${token}` }
  });
};

export const deletePost = async (id, token) => {
  return axios.delete(`${API_URL}/post/${id}`, {
    headers: { Authorization: `Bearer ${token}` }
  });
};

export const getComments = async (postId) => {
  return axios.get(`${API_URL}/comments/${postId}/comments`);
};

export const addComment = async (postId, content, token) => {
  return axios.post(`${API_URL}/comments/${postId}`, { content }, {
    headers: { Authorization: `Bearer ${token}` }
  });
};