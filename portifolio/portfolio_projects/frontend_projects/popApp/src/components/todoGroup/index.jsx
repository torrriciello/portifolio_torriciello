import { SubHeading } from "../SubHeading";
import { ToDoList } from "../ToDoList";
import { ToDoItem } from "../ToDoItem";

export function TodoGroup({ items, heading }) {
  return (
    <>
      <SubHeading>{heading}</SubHeading>
      <ToDoList>
        {items
          .map(function (t) {
            return (
              <ToDoItem
                key={t.id}
                item={t}
              />
            );
          })}
      </ToDoList>
    </>
  );
}
