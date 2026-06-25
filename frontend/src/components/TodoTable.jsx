import TodoRow from "./TodoRow";
import "./TodoTable.css";

function TodoTable() {
    const todos = [ 
        {
            id: 1,
            title: "Todo 1",
            category: "Category 1",
            priority: "LOW",
            status: "PENDING"
        },
        {
            id: 2,
            title: "Todo 2",
            category: "Category 2",
            priority: "MEDIUM",
            status: "IN PROGRESS"
        },
        {
            id: 3,
            title: "Todo 3",
            category: "Category 2",
            priority: "LOW",
            status: "COMPLETED"
        }
    ];

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
                {todos.map(todo => 
                <TodoRow key={todo.id} todo={todo} />)}
            </tbody>
        </table>
    )

}

export default TodoTable;
