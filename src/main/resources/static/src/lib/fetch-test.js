const { log, mold } = require('./fetch');
const { get, post } = mold();

get('https://jsonplaceholder.typicode.com/posts/1').then(({ json }) => log('/posts/1', json));

const payload = {
  userId: 1,
  id: 0,
  title: 'sunt aut facere repellat provident occaecati excepturi optio reprehenderit',
  body:
    'quia et suscipit↵suscipit recusandae consequuntur …strum rerum est autem sunt rem eveniet architecto',
};
post('https://jsonplaceholder.typicode.com/posts', payload).then(({ http }) => log('http', http));
