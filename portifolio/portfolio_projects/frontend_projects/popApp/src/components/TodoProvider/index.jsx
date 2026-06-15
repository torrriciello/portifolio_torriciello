import TodoContext from "./TodoContext";
import { useEffect, useState } from "react";

const TODOS = "todos";

export function TodoProvider({ children }) {
  const savedTodos = localStorage.getItem(TODOS);

  const [todos, setTodos] = useState(savedTodos ? JSON.parse(savedTodos) : []);

  useEffect(() => {
    localStorage.setItem(TODOS, JSON.stringify(todos));
  }, [todos]);

  const addTodo = (formData) => {
    const description = formData.get("description");
    if (!description?.trim()) return;

    setTodos((prevState) => {
      const todo = {
        id: Date.now(),
        description: description,
        completed: false,
        createdAt: new Date().toISOString(),
      };
      return [...prevState, todo];
    });
  };

  const toggleTodoCompleted = (todo) => {
    setTodos((prevState) =>
      prevState.map((t) =>
        t.id === todo.id ? { ...t, completed: !t.completed } : t,
      ),
    );
  };

  const deleteTodo = (todo) => {
    setTodos((prevState) => prevState.filter((t) => t.id !== todo.id));
  };

  return (
    <TodoContext.Provider
      value={{ todos, addTodo, toggleTodoCompleted, deleteTodo }}
    >
      {children}
    </TodoContext.Provider>
  );
}
