import React from 'react';
import {useHistory} from 'react-router-dom';

const RescheduleMovie = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        time : "00:00",
        date : "2021-01-01"});

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const time = formData.time;
        const date = formData.date;

        props.onReschedule(props?.scheduledMovie?.id?.id, time,date);
        history.push("/scheduledMovies");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-6">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label htmlFor="time">New time</label>
                        <input type="time"
                               className="form-control"
                               id="time"
                               name="time"
                               placeholder="Enter time"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="date">New date</label>
                        <input type="date"
                               className="form-control"
                               id="date"
                               name="date"
                               placeholder="Enter date"
                               required
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>

                    </form>
            </div>
            <div className={"col-md-6"}>
                <img height={"600px"} src={"https://www.pngall.com/wp-content/uploads/2018/06/Cinema-Transparent.png"}/>
            </div>

        </div>
    )
}

export default RescheduleMovie;
