import React from 'react';
import { Button } from '../../components/Button';
import logo from '../../assets/logo.svg';
import banner from '../../assets/banner.png'; 
import './registerLocation.css';
import { FormRegisterLocation } from '../../components/FormRegisterLocation';

export function RegisterLocation() {
    return (
        <div className="register-page">
            {/* Lado Esquerdo: Imagem do Banner */}
            <div className="register-banner">
                <img src={banner} alt="Banner de Registro" />
            </div>

            {/* Lado Direito: Todo o conteúdo do formulário */}
            <div className="register-content">
                <div className="register-container-inner">
                    <div className="register-header">
                        <img src={logo} alt="Logo ReciclaPro" className="register-logo" />
                        <h2>Olá,<br />Pronto para começar?</h2>
                    </div>
                    
                    {/* O formulário que limpamos no passo anterior */}
                    <FormRegisterLocation />
    
                    {/* Botões de ação alinhados com a largura do form */}
                    <div className="register-actions">
                        <Button type="button" variant="secondary">Voltar</Button>
                        <Button type="submit">Prosseguir</Button>
                    </div>
                </div>
            </div>
        </div>
    );
}