import './TodoFilters.css';

function TodoFilters({ category, setCategory, status, setStatus, categories }) {

    return (
        <div className='todo-filters'>

            <div className='filter-group'>
                <label>Category</label>
                <select value={category} onChange={(event) => {setCategory(event.target.value);}}>
                    <option value="">All Categories</option>
                    {categories.map((category) => (
                        <option key={category} value={category}> 
                            {category}
                        </option>
                    ))}
                </select>
                <p>Selected Category: {category}</p>
            </div>

            <div className='filter-group'>
                <label>Status</label>
                <select value={status} onChange={(event) => {setStatus(event.target.value)}}>
                    <option value="">All Statuses</option>
                    <option value="PENDING">PENDING</option>
                    <option value="IN PROGRESS">IN PROGRESS</option>
                    <option value="COMPLETED">COMPLETED</option>
                </select>
                <p>Selected Status: {status}</p>
            </div>

        </div>
    )
}

export default TodoFilters;