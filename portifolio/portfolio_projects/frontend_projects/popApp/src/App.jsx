import { useContext } from "react";
import { ChecklistsWrapper } from "./components/ChecklistsWrapper";
import { Container } from "./components/Container";
import { Dialog } from "./components/Dialog";
import { FabButton } from "./components/FabButton";
import { Footer } from "./components/Footer";
import { Header } from "./components/Header";
import { Heading } from "./components/Heading";
import { IconPlus, IconSchool } from "./components/icons";
import { ToDoForm } from "./components/ToDoForm";
import { Button } from "./components/Button";
import { TodoGroup } from "./components/todoGroup";
import { TodoProvider } from "./components/TodoProvider";
import { EmptyState } from "./components/EmptyState";
import TodoContext from "./components/TodoProvider/TodoContext";

function App() {
  const { todos, addTodo, showDialog, openFormTodoDialog, closeFormTodoDialog, selectedTodo, editTodo } = useContext(TodoContext);

    const handleFormSubmit = (formData) => {
      if (selectedTodo) {
        editTodo(formData)
      } else {
        addTodo(formData)
      }
      closeFormTodoDialog()
    }

  return (
    <main>
      <Container>
        <Header>
          <Heading>
            <IconSchool /> Plano de estudos
          </Heading>
        </Header>

        <ChecklistsWrapper>
          <TodoGroup
            heading="Para estudar"
            items={todos.filter((t) => !t.completed)}
          />

          {todos.length == 0 && <EmptyState />}  
          <TodoGroup
            heading="Concluído"
            items={todos.filter((t) => t.completed)}
          />

          <Footer>
            <Dialog isOpen={showDialog} onClose={closeFormTodoDialog}>
              <ToDoForm onSubmit={handleFormSubmit} defaultValue={selectedTodo?.description}/>
            </Dialog>
            <FabButton onClick={() => openFormTodoDialog()}>
              <IconPlus />
            </FabButton>
          </Footer>
        </ChecklistsWrapper>
      </Container>
    </main>
  );
}
export default App;
