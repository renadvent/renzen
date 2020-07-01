export function JSONLoader(props) {

    return fetch(props, {method: 'GET'})
        .then(response => response.json())
}

//most liikely will return an array of objects when used with MongoDB

export default JSONLoader