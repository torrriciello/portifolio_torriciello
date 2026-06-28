import React from 'react';
import './Input.css';

export function Input({ type, placeholder, value, onChange }) {
  return (
    <input 
      type={type} 
      placeholder={placeholder} 
      value={value} 
      onChange={onChange} 
      className="reciclapro-input" 
    />
  );
}