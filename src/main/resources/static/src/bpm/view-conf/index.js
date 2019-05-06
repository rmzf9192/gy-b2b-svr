const { log, get, post } = require('../../lib/fetch');
const { Q, C } = require('../apis');

class EdpBpmFormViewConf extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      config: {
        defId: 'SuppliperItem:1:87323',
        nodeId: 'purchasingManager',
        mode: 'create',
        conf: '{}',
      },
    };

    this.handleNodeChange = this.handleNodeChange.bind(this);
    this.handleChange = this.handleChange.bind(this);

    this.handleLoad = this.handleLoad.bind(this);
    this.handleSave = this.handleSave.bind(this);

    this.styles = {
      label: { display: 'inline-block', width: '60px' },
      input: { width: '40em' },
      inbox: { width: '40em', height: '10em' },
    };
  }

  handleNodeChange(event) {
    const { config } = this.state;
    const $el = event.target;
    this.setState({
      config: Object.assign({}, config, { nodeId: $el.value }),
    });
  }
  handleChange(event) {
    const { config } = this.state;
    const $el = event.target;
    this.setState({
      config: Object.assign({}, config, { [$el.name]: $el.value }),
    });
  }

  handleLoad(event) {
    const { config } = this.state;
    const params = Object.assign({}, config);
    params.conf = void 0;
    get(Q.viewConf, params).then(({ json }) =>
      this.setState({
        config: Object.assign({}, config, { conf: JSON.stringify(json) }),
      })
    );
  }
  handleSave(event) {
    const { config } = this.state;
    console.log(config);
    post(Q.viewConf, config);
  }

  render() {
    const { config } = this.state;
    const { nodes } = this.props;
    const modes = ['create', 'edit', 'view', 'approval'];
    const styles = this.styles;
    return (
      <div>
        <select onChange={this.handleNodeChange}>
          {nodes.map(node => (
            <option key={node.id} value={node.id}>
              {node.id} - {node.name}
            </option>
          ))}
        </select>
        <hr />
        <div>
          <label style={styles.label}>defId</label>
          <input
            style={styles.input}
            name="defId"
            value={config.defId}
            onChange={this.handleChange}
          />
        </div>
        <div>
          <label style={styles.label}>nodeId</label>
          <input
            style={styles.input}
            name="nodeId"
            value={config.nodeId}
            onChange={this.handleChange}
          />
        </div>
        <div>
          <label style={styles.label}>mode</label>
          <select style={styles.input} name="mode" value={config.mode} onChange={this.handleChange}>
            {modes.map(mode => (
              <option key={mode} value={mode}>
                {mode}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label style={styles.label}>conf</label>
          <textarea
            style={styles.inbox}
            name="conf"
            value={config.conf}
            onChange={this.handleChange}
          />
        </div>
        <div>
          <button type="button" onClick={this.handleLoad}>
            Load View Configuration
          </button>
          <button type="button" onClick={this.handleSave}>
            Save View Configuration
          </button>
        </div>
      </div>
    );
  }
}

const viewConf = get(Q.userTasks, { defId: 'SuppliperItem:1:87323' }).then(({ json }) => (
  <EdpBpmFormViewConf nodes={json} />
));

module.exports = viewConf;
