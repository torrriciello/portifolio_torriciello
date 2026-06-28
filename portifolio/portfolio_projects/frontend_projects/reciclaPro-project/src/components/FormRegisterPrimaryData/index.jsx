import banner from "../../assets/banner.png";
import "./registerFormPrimaryData.css";
import { Input } from "../Input";

export function RegisterFormPrimaryData() {
    return (
        <div className="register-container">
            <div className="form-wrapper">
                <form onSubmit={(e) => e.preventDefault()}>
                    <Input type="text" placeholder="Nome" />
                    <Input type="text" placeholder="CPF" />
                    <Input type="email" placeholder="E-mail" />
                </form>
            </div>
        </div>
    );
}