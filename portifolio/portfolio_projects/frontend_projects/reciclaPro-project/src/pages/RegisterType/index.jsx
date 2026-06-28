import React, { useState } from 'react';
import { Button } from '../../components/Button';
import logo from '../../assets/logo.svg';
import banner from '../../assets/banner.png';
import './RegisterType.css';
import { TypeForm } from '../../components/Type';

export function RegisterType() {
    const [userType, setUserType] = useState(''); // O estado continua aqui

    const handleProceed = (e) => {
        e.preventDefault();
        if (!userType) {
            alert('Por favor, selecione uma opção para prosseguir.');
            return;
        }
        console.log('Tipo de usuário selecionado:', userType);
    };

    return (
        <div className="register-type-container">
            <div className="register-type-card-box">
               
                <div className="register-type-left-side">
                    <img src={banner} alt="Ilustração Reciclagem" className="register-type-banner-img" />
                </div>

                <div className="register-type-right-side">
                    <div className="register-type-content-wrapper">

                        <header className="register-type-header">
                            <img src={logo} alt="ReciclaPro Logo" className="register-type-brand-logo" />
                            <h1 className="register-type-title">Olá,<br />Pronto para começar?</h1>
                        </header>

                        <TypeForm userType={userType} setUserType={setUserType} />

                        <footer className="register-type-action-buttons">
                            <Button variant="secondary" onClick={() => console.log('Voltar clicado')}>
                                Voltar
                            </Button>
                            <Button variant="primary" onClick={handleProceed}>
                                Prosseguir
                            </Button>
                        </footer>

                    </div>
                </div>

            </div>
        </div>
    );
}