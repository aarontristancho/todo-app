import './App.css'
import Header from './components/Header'
import TodoFilters from './components/TodoFilters';
import TodoTable from './components/TodoTable';
import { useState } from "react";

function App() {

const [category, setCategory] = useState('');
const [status, setStatus] = useState('');

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
      <TodoTable/>
    </div>
  );
}

export default App;