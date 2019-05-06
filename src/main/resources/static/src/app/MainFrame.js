const {
  Container,
  Sidebar,
  Sidenav,
  Header,
  Navbar,
  Nav,
  Dropdown,
  Footer,
  Icon,
  Content,
  FlexboxGird,
} = rsuite;

const NavToggle = require('./NavToggle');

const headerStyles = {
  padding: 18,
  fontSize: 16,
  height: 56,
  background: '#34c3ff',
  color: ' #fff',
  whiteSpace: 'nowrap',
  overflow: 'hidden',
};

class AppPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      expand: true,
    };
    this.handleToggle = this.handleToggle.bind(this);
    this.handleSelect = this.handleSelect.bind(this);
  }
  handleToggle() {
    this.setState({
      expand: !this.state.expand,
    });
  }
  handleSelect(routePath) {
    this.props.history.push(routePath);
  }
  render() {
    const { expand } = this.state;
    return (
      <div className="show-fake-browser sidebar-page">
        <Container>
          <Sidebar
            style={{ display: 'flex', flexDirection: 'column' }}
            width={expand ? 260 : 56}
            collapsible
          >
            <Sidenav.Header>
              <div style={headerStyles}>
                <a href="./" style={{ color: '#fff' }}>
                  <Icon icon="logo-analytics" size="lg" style={{ verticalAlign: 0 }} />
                  <span style={{ marginLeft: 12 }}> EDP</span>
                </a>
              </div>
            </Sidenav.Header>
            <Sidenav expanded={expand} appearance="subtle">
              <Sidenav.Body>
                <Nav>
                  <Nav.Item icon={<Icon icon="dashboard" />} eventKey="dashboard" href="#/dashboard">
                    Dashboard
                  </Nav.Item>
                  <Nav.Item icon={<Icon icon="group" />} eventKey="sec" href="#/sec">
                    Authc &amp; Authz (sec)
                  </Nav.Item>
                  <Dropdown
                    eventKey="3"
                    onSelect={this.handleSelect}
                    trigger="hover"
                    title="Process Mgmt. (bpm)"
                    icon={<Icon icon="magic" />}
                  >
                    <Dropdown.Item eventKey="/bpm/showcase" onSelect={this.handleSelect}>
                      Showcase
                    </Dropdown.Item>
                    <Dropdown.Item eventKey="/bpm/model-conf" onSelect={this.handleSelect}>
                      Model Customization
                    </Dropdown.Item>
                    <Dropdown.Item eventKey="/bpm/view-conf" onSelect={this.handleSelect}>
                      Form View Customization
                    </Dropdown.Item>
                    <Dropdown.Item eventKey="/bpm/task-auto" onSelect={this.handleSelect}>
                      Task Auto Completion
                    </Dropdown.Item>
                    <Dropdown.Item eventKey="/bpm/proc-oper" onSelect={this.handleSelect}>
                      Runtime Modification
                    </Dropdown.Item>
                  </Dropdown>
                  <Nav.Item icon={<Icon icon="file" />} eventKey="sfs" href="#/sfs">
                    Storage Service (sfs)
                  </Nav.Item>
                </Nav>
              </Sidenav.Body>
            </Sidenav>
            <NavToggle expand={expand} onChange={this.handleToggle} />
          </Sidebar>

          <Container>
            <Header>
              <h2 className="title">EDP Showcase</h2>
            </Header>
            <Content>Content</Content>
          </Container>
        </Container>
      </div>
    );
  }
}

module.exports = AppPage;
