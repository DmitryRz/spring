import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const AuthPage = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login, register } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    let success;
    if (isLogin) {
      success = await login(username, password);
    } else {
      success = await register(username, email, password);
      if (success) {
        success = await login(username, password);
      }
    }

    if (success) {
      navigate('/');
    } else {
      setError(isLogin ? 'Неверные данные для входа' : 'Ошибка регистрации');
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', padding: '20px' }}>
      <h1>{isLogin ? 'Вход' : 'Регистрация'}</h1>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label>
            Имя пользователя:
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              style={{ width: '100%', padding: '8px' }}
            />
          </label>
        </div>
        
        {!isLogin && (
          <div style={{ marginBottom: '10px' }}>
            <label>
              Email:
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                style={{ width: '100%', padding: '8px' }}
              />
            </label>
          </div>
        )}
        
        <div style={{ marginBottom: '10px' }}>
          <label>
            Пароль:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              style={{ width: '100%', padding: '8px' }}
            />
          </label>
        </div>
        
        <button type="submit" style={{ width: '100%', padding: '10px', background: '#007bff', color: 'white', border: 'none' }}>
          {isLogin ? 'Войти' : 'Зарегистрироваться'}
        </button>
      </form>
      
      <button 
        onClick={() => setIsLogin(!isLogin)}
        style={{ marginTop: '10px', width: '100%', padding: '10px', background: 'transparent', border: 'none', color: '#007bff' }}
      >
        {isLogin ? 'Нет аккаунта? Зарегистрироваться' : 'Уже есть аккаунт? Войти'}
      </button>
    </div>
  );
};

export default AuthPage;