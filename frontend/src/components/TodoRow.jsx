import "./TodoRow.css";

function TodoRow({ todo }) {

  return (
    <tr className="tableRow">
      <td>{todo.title}</td>
      <td>{todo.category}</td>
      <td>{todo.priority}</td>
      <td>{todo.status}</td>
    </tr>
  );
}

export default TodoRow;
