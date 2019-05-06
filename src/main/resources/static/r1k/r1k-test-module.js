console.log('[R1K] call:', arguments);

const hello = 'world';
module.exports = hello;

const m1kModule = arguments[2];
const { l, t, g } = m1kModule;
console.log('[R1K] `l` is URL:', l);
assert(l, g, '`l` === `g`');
console.log('[R1K] `t` is content of the module:', t);
assert(m1kModule.exports, module.exports, '`l` === `g`');
