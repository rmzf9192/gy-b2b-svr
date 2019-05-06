const styles = {
  child: {
    display: 'inline-block',
    margin: '1em',
    padding: '1em',
    border: '1px dashed #000',
    width: '20em',
    textAlign: 'center',
  },
  check: {
    padding: '1em',
    border: '1px solid #000',
  },
};

class Child extends React.Component {
  check() {
    console.log('[Child] check');
  }
  render() {
    return <div style={styles.child}>Child</div>;
  }
}

class Parent extends React.Component {
  constructor(props) {
    super(props);
    this.childRef = React.createRef();
    this.check = this.check.bind(this);
  }
  check() {
    console.log('[Parent] check');
    this.childRef.current.check();
  }
  render() {
    return (
      <div>
        <Child ref={this.childRef} />
        <button style={styles.check} onClick={this.check}>
          Check!
        </button>
      </div>
    );
  }
}

const AnotherParent = () => {
  const childRef = React.createRef();

  function check() {
    console.log('[Parent] check');
    childRef.current.check();
  }

  return (
    <div>
      <Child ref={childRef} />
      <button style={styles.check} onClick={check}>
        Check!
      </button>
    </div>
  );
};

module.exports = <AnotherParent />;
