import React from 'react';

class DummyComponent extends React.Component {

    constructor(props) {
        super(props)
        this.reverseString = this.reverseString.bind(this);
    }

    reverseString (str) {
        if (str === "") {
            return str;
        } return this.reverseString(str.substr(1)) + str.charAt(0);
    }

    render () {
        var hw = this.reverseString('sdrawkcab dlrow olleh');
        return (
            <h4>{hw}</h4>
        );
    }
}

export default DummyComponent;