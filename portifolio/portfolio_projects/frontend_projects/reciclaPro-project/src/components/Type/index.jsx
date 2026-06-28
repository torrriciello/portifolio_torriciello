import React from 'react';
import contribuinte from '../../assets/contribuinte.png';
import catador from '../../assets/catador.png';
import './TypeForm.css';

export function TypeForm({ userType, setUserType }) {
  return (
    <main className="register-type-selection-area">
      <div className="register-type-cards">

        {/* Card Catador */}
        <div
          className={`type-card ${userType === 'catador' ? 'active' : ''}`}
          onClick={() => setUserType('catador')}
        >
          <img src={catador} alt="Avatar Catador" className="type-card-avatar" />
          <span className="type-card-label">Sou Catador</span>
        </div>

        {/* Card Contribuinte */}
        <div
          className={`type-card ${userType === 'contribuinte' ? 'active' : ''}`}
          onClick={() => setUserType('contribuinte')}
        >
          <img src={contribuinte} alt="Avatar Contribuinte" className="type-card-avatar" />
          <span className="type-card-label">Sou Contribuinte</span>
        </div>

      </div>
    </main>
  );
}