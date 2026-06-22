import TodoRow from "./TodoRow";

function TodoTable() {
    return (
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Priority</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <TodoRow></TodoRow>
            </tbody>
        </table>
    )

}

export default TodoTable;
