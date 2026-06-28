import React from 'react';
import { Button } from '../../components/Button';
import { RegisterFormPrimaryData } from '../../components/FormRegisterPrimaryData';
import logo from '../../assets/logo.svg';
import banner from '../../assets/banner.png'; 
import './registerPrimaryData.css';

export function RegisterPrimaryData() {
    return (
        <div className="register-page">
            <div className="register-banner">
                <img src={banner} alt="Banner de Registro" />
            </div>

            <div className="register-content">
                <div className="register-header">
                    <img src={logo} alt="Logo da Empresa" className="register-logo" />
                    <h2>Olá <br />Pronto para começar?</h2>
                </div>
                
                <RegisterFormPrimaryData />

                <div className="register-actions">
                    <Button type="button" variant="secondary">Voltar</Button>
                    <Button type="submit">Prosseguir</Button>
                </div>
            </div>
        </div>
    );
}