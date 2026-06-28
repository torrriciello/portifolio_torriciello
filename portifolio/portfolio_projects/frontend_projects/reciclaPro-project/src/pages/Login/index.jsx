import React, { useState } from 'react';
import { Input } from '../../components/Input';
import { Button } from '../../components/Button';
import logo from '../../assets/logo.svg'; 
import banner from '../../assets/banner.png';  
import './Login.css';

export function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Login dados:', { email, password });
  };

  return (
    <div className="login-screen-container">
      <div className="login-card-box">
        
        <div className="login-left-side">
          <div className="login-content-wrapper">
            
            <header className="login-header">
              <img src={logo} alt="ReciclaPro Logo" className="login-brand-logo" />
              <h1 className="login-title">Olá,<br />Bem-vindo de volta!</h1>
            </header>

            <form onSubmit={handleSubmit} className="login-main-form">
              <div className="login-fields">
                <Input 
                  type="email" 
                  placeholder="Digite o seu e-mail" 
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
                <Input 
                  type={showPassword ? "text" : "password"} 
                  placeholder="Senha" 
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </div>

              <div className="login-utilities">
                <label className="login-checkbox-label">
                  <input 
                    type="checkbox" 
                    checked={showPassword} 
                    onChange={() => setShowPassword(!showPassword)} 
                    className="login-checkbox" 
                  />
                  <span>Mostrar a minha senha</span>
                </label>
                <a href="#esqueci" className="login-forgot-link">Esqueci a minha senha</a>
              </div>

              <div className="login-action-buttons">
                <Button variant="secondary" onClick={() => console.log('Criar conta clicado')}>
                  Criar conta
                </Button>
                <Button type="submit" variant="primary">
                  Login
                </Button>
              </div>
            </form>

          </div>
        </div>

        {/* LADO DIREITO: ILUSTRAÇÃO */}
        <div className="login-right-side">
          <img src={banner} alt="Ilustração Reciclagem" className="login-banner-img" />
        </div>

      </div>
    </div>
  );
}