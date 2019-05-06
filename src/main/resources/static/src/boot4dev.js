const { log, get, post, form, xsrf } = require('./lib/fetch');
const auth = token => {
  const [ent_code, login_no, password] = token.split('|');
  return form('/sec/login', {
    ent_code,
    login_no,
    password: password || 'password',
    captcha: '1234',
  });
};

const user = () => get('/sec/principal');
const boot = token =>
  xsrf('/sec/xsrf')
    .then(() => auth(token))
    .then(user)
    .then(({ json }) => {
      log('user:', json);
      return json;
    });

module.exports = boot;
