import React from 'react';
import './Button.css';

export function Button({ children, onClick, variant = 'primary', type = 'button' }) {
  return (
    <button 
      type={type} 
      onClick={onClick} 
      className={`reciclapro-btn ${variant}`}
    >
      {children}
    </button>
  );
}