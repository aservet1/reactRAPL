import './App.css';
import EnergySampler from './components/EnergySampler.js';
import DummyComponent from './components/DummyComponent.js';
import Header from './components/Header.js'

//Import all needed Component for this tutorial
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Link,
  Redirect
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      printf("hello w0rld\n");
      <Router>
        <Header/>
        <Route exact path="/" component={EnergySampler} />
        <Route exact path="/dummy" component={DummyComponent} />
      </Router>
    </div>
  );
}

export default App;
