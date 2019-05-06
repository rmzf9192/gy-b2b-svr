const App = require('./app/App');
const boot = require('./boot4dev');
module.exports = boot('E001|0').then(user => <App />);
