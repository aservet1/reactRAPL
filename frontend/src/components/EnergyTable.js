import React from 'react';
import Table from './Table.js';
import './EnergyTable.css'

class EnergyTable extends React.Component {

    render() {

        return (
            <div className="EnergyTable">
                <div className='inputSection'>
                    <div>
                        <input
                            type='text'
                            placeholder={this.props.placeholder}
                            onChange={this.props.onTextChange}
                        />
                    </div>
                    <div>
                        <input
                            type='button'
                            value={this.props.buttonText}
                            onClick={this.props.buttonOnClick}
                        />
                    </div>
                    {/*
                        https://stackoverflow.com/questions/44656610/download-a-string-as-txt-file-in-react
                        TODO: make a download button, to download data as JSON or CSV
                    */}
                </div>
                <Table
                    id = 'TBL'
                    data = {this.props.data}
                />
            
            </div>	
        );
    }
}

export default EnergyTable;
