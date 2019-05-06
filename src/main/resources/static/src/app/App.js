const MainFrame = require('./MainFrame');
const ThemeContext = require('./ThemeContext');

const { createHashHistory } = History;

const history = createHashHistory({
  basename: '', // The base URL of the app (see below)
  hashType: 'slash', // The hash type to use (see below)
  // A function to use to confirm navigation with the user (see below)
  getUserConfirmation: (message, callback) => callback(window.confirm(message)),
});

class App extends React.Component {
  componentDidMount() {
    this.unlisten = history.listen((location, action) => {
      // location is an object like window.location
      console.log('[EDP-MVC]', action, location.pathname, location.state);
      RR(location.pathname, document.querySelector('.rs-content'));
    });
  }
  componentWillUnmount() {
    this.unlisten();
  }
  render() {
    return (
      <ThemeContext.Provider>
        <MainFrame history={history} />
      </ThemeContext.Provider>
    );
  }
}

module.exports = App;
