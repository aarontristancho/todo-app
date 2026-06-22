import { useState } from "react";

function TodoFilters() {
    const [category, setCategory] = useState('');
    const [status, setStatus] = useState('');

    return (
        <div>
            <label>Category</label>
            <select value={category} onChange={(event) => {setCategory(event.target.value);}}>
                <option value="">All Categories</option>
            </select>
            <p>Selected Category: {category}</p>

            <label>Status</label>
            <select value={status} onChange={(event) => {setStatus(event.target.value)}}>
                <option value="">All Statuses</option>
                <option value="PENDING">PENDING</option>
                <option value="IN_PROGRESS">IN PROGRESS</option>
                <option value="COMPLETED">COMPLETED</option>
            </select>
            <p>Selected Status: {status}</p>
        </div>
    )
}

export default TodoFilters;