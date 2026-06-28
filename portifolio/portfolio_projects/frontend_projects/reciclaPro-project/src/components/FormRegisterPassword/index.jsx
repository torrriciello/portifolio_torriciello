import { useState } from "react";
import { Input } from "../Input";
import "./formRegisterPassword.css";

export function FormRegisterPassword() {
    const [showPassword, setShowPassword] = useState(false);

    const handleTogglePassword = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div className="register-form-container">
            <form id="register-password-form" className="register-password-form">
                <div className="inputs-group">
                    <Input 
                        type={showPassword ? "text" : "password"} 
                        placeholder="Senha" 
                    />
                    <Input 
                        type={showPassword ? "text" : "password"} 
                        placeholder="Confirmar Senha" 
                    />
                </div>
                
                <div className="show-password-container">
                    <input 
                        type="checkbox" 
                        id="show-password" 
                        checked={showPassword}
                        onChange={handleTogglePassword}
                    />
                    <label htmlFor="show-password">Mostrar a minha senha</label>
                </div>
            </form>
        </div>
    );
}