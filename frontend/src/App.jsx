import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';
import TodoTable from './components/TodoTable';
import { useState } from "react";

function App() {

const [category, setCategory] = useState('');
const [status, setStatus] = useState('');
const [todos, setTodos] = useState([ 
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
]);

  return (
    <div className="app">
      <Header
        title='Todo App'
        description="Manage your tasks efficiently"
      />
      <TodoFilters
        category={category}
        setCategory={setCategory}
        status={status}
        setStatus={setStatus}
      />
      <TodoTable
        category={category}
        status={status}
        todos={todos}
      />
    </div>
  );
}

export default App;