import TodoRow from "./TodoRow";
import "./TodoTable.css";

function TodoTable({ category, status, todos }) {

    const filteredTodos = todos.filter(todo => {
        const matchesCategory = (category === '' || todo.category === category) 
        const matchesStatus = (status === '' || todo.status === status)
        return matchesCategory && matchesStatus
    })

    return (
        <table className="todo-table">
            <thead className="todo-table-head">
                <tr>
                    <th>TASK</th>
                    <th>CATEGORY</th>
                    <th>PRIORITY</th>
                    <th>STATUS</th>
                </tr>
            </thead>
            <tbody>
                {filteredTodos.map(todo => 
                <TodoRow key={todo.id} todo={todo} />)}
            </tbody>
        </table>
    )

}

export default TodoTable;
