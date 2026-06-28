import React from 'react';
import { Button } from '../../components/Button';
import { RegisterFormPrimaryData } from '../../components/FormRegisterPrimaryData';
import logo from '../../assets/logo.svg';
import banner from '../../assets/banner.png';
import { FormRegisterPassword } from '../../components/FormRegisterPassword';
import './registerPassword.css';

export function RegisterPassword() {
    return (
        <div className="register-page">
            <div className="register-banner">
                <img src={banner} alt="Banner de Registro" />
            </div>

            <div className="register-content">
                <div className="register-container-inner">
                    <div className="register-header">
                        <img src={logo} alt="Logo ReciclaPro" className="register-logo" />
                        <h2>Confirme sua senha</h2>
                    </div>

                    <FormRegisterPassword />

                    <div className="register-actions">
                        <Button type="button" variant="secondary">Voltar</Button>
                        <Button type="submit">Prosseguir</Button>
                    </div>
                </div>
            </div>
        </div>
    )
}