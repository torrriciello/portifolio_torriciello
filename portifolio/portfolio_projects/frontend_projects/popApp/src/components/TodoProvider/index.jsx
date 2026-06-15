import TodoContext from "./TodoContext";
import { useState } from "react";

export function TodoProvider({ children }) {
  const [todos, setTodos] = useState([
    {
      id: 1,
      description: "JSX e componentes",
      completed: false,
      createdAt: "2022-10-31",
    },
    {
      id: 2,
      description: "Controle de inputs e formulários controlados",
      completed: true,
      createdAt: "2022-10-31",
    },
  ]);

  const addTodo = (formData) => {
    const description = formData.get("description");
    setTodos((prevState) => {
      const todo = {
        id: prevState.length + 1,
        description: description,
        completed: false,
        createdAt: new Date().toISOString(),
      };
      return [...prevState, todo];
    });
  };

  const toggleTodoCompleted = (todo) => {
    setTodos((prevState) => {
      return prevState.map((t) => {
        if (t.id === todo.id) {
          return {
            ...t,
            completed: !t.completed,
          };
        }
        return t;
      });
    });
  };

  const deleteTodo = (todo) => {
    setTodos((prevState) => {
      return prevState.filter((t) => t.id != todo.id);
    });
  };

  return (
    <TodoContext.Provider
      value={{ todos, addTodo, toggleTodoCompleted, deleteTodo }}
    >
      {children}
    </TodoContext.Provider>
  );
}
