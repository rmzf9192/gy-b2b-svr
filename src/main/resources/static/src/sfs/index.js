const { url } = require('../lib/fetch');
const { Q, C } = require('./apis');
const {
  Panel,
  Uploader,
  Form,
  FormGroup,
  ControlLabel,
  FormControl,
  HelpBlock,
  ButtonToolbar,
  Button,
} = rsuite;

const UPLOAD_EXT = 'image/*';
const UPLOAD_URL = url(C.upload);

class EdpUploader extends React.Component {
  render() {
    return <Uploader accept={UPLOAD_EXT} defaultFileList={this.props.value} action={UPLOAD_URL} />
  }
}

class EdpSfsDemo extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      form: {
        repoCode: 'dev01',
        clsPath: 'item/c3',
        dataKey: '',
        files: [],
      },
    };
    this.handleChange = (form, event) => {
      this.setState({ form });
    };
  }
  render() {
    return (
      <Panel header={<h3>SFS (Simple File Service)</h3>} bordered>
        <Form formValue={this.state.form} onChange={this.handleChange}>
          <FormGroup>
            <ButtonToolbar>
              <FormControl name="files" accepter={EdpUploader} />
            </ButtonToolbar>
          </FormGroup>
          <FormGroup>
            <ControlLabel>dataKey</ControlLabel>
            <FormControl name="dataKey" />
          </FormGroup>
          <FormGroup>
            <ControlLabel>repoCode</ControlLabel>
            <FormControl name="repoCode" />
          </FormGroup>
          <FormGroup>
            <ControlLabel>clsPath</ControlLabel>
            <FormControl name="clsPath" />
          </FormGroup>
        </Form>
      </Panel>
    );
  }
}

module.exports = <EdpSfsDemo />;
