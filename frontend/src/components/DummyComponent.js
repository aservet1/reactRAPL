import React from 'react';

class DummyComponent extends React.Component {

    reverseString = (str) => {
        if (str === "") {
            return str;
        } return str.substr(1) + str.charAt(0);
    }

    render () {
        var hw = this.reverseString('hello world')
        return (
            <h4>{hw}</h4>
        );
    }
}

export default DummyComponent;