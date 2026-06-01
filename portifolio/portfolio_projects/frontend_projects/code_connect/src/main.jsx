import "./index.css";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { Login } from "./pages/Login/index.jsx";
import { Register } from "./pages/Register/index.jsx";
import { Feed } from "./pages/Feed/index.jsx";
import { BlogPost } from "./pages/BlogPost/index.jsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { ProtectedRouter } from "./components/ProctedRouter/index.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/auth">
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
        </Route>
        <Route path="/">
          <Route
            path=""
            element={
              <ProtectedRouter>
                <Feed />
              </ProtectedRouter>
            }
          />
          <Route
            path="/blog-post"
            element={
              <ProtectedRouter>
                <BlogPost />
              </ProtectedRouter>
            }
          />
        </Route>
      </Routes>
    </BrowserRouter>
  </StrictMode>,
);
