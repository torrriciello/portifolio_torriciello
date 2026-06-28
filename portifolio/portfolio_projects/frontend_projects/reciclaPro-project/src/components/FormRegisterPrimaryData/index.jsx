import banner from "../../assets/banner.png";
import "./registerFormPrimaryData.css";

export function RegisterFormPrimaryData() {
    return (
        <div className="register-container">
            <div className="form-wrapper">
                <form onSubmit={(e) => e.preventDefault()}>
                    <input type="text" placeholder="Nome" />
                    <input type="text" placeholder="CPF" />
                    <input type="email" placeholder="E-mail" />
                </form>
            </div>
        </div>
    );
}