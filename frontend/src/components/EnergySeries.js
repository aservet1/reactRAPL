
import React from 'react';
import axios from 'axios';
import ReactFlexyTable from "react-flexy-table"
import 'react-flexy-table/dist/index.css'

class EnergySeries extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            "EnergySeries": [{"energySeries":"~~~~~"}],
            "duration": 0,
            "samplingRate": 0
        }
    }

    requestEnergySeries = (e) => {
        e.preventDefault()
        const d = this.state.duration;
        const s = this.state.samplingRate;
        axios.get(`http://localhost:8080/energy/diff/list:${d},${s}`) // TODO you've gotta change this to a legit formal URL format
            .then(res => {
                this.setState({"EnergySeries": res.data})
            }).catch(err =>{
                console.log('fail!!!!!!!!!!')
                console.log("err:",err)
                this.setState({})
            })
    }

    handleChangeSeriesParams = (e) => {
        const params = e.target.value.split(",");
        this.setState({
            "duration" : params[0] ,
            "samplingRate" : params[1]
        });
    }

    render() {
        const downloadExcelProps = {
          type: 'filtered',
          title: 'test',
          showLabel: true
        }

        return (
            <div>
                <div className="EnergySeries">
                    <input type="text" placeholder="duration,samplingRate" onChange={this.handleChangeSeriesParams}/>
                    <input type="button" value="EnergySeries()" onClick={this.requestEnergySeries}/>
                    <br></br>
                    <ReactFlexyTable
                      data={this.state.EnergySeries}
                      caseSensitive={true}
                      showExcelButton={true}
                      downloadExcelProps={downloadExcelProps}/>
                </div>
            </div>
        );
    }
}

export default EnergySeries;
