import React from 'react';

class Countdown extends React.Component {

    // constructor(props) {
    //     super(props)
    //     this.state = {
    //         "start": 10
    //     }
    // }

    // countdown = () => {
    //     var n = this.state.start;
    //     var elapsedTime = 0;
    //     for (;n > 0;n--){
    //         setTimeout(()=>console.log(`Timer: ${n}`),1000+elapsedTime);
    //         elapsedTime += 1000;
    //     }
    //     console.log('done');
    // }


    async countdown = () => {
        function sleep(ms) {
          return new Promise(resolve => setTimeout(resolve, ms));
        }
    
        var incr = 1000;
        var time = 0;
        
        for (var i = 0; i < 25; i++) {
            console.log(i);
            await sleep(1000);
            
        }
    }


    render () {
        this.countdown()
        return (
            <h4 text-align="center" >hello</h4>
        );
    }
}

export default Countdown;