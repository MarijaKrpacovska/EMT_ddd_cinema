import React from 'react';
import {useHistory} from 'react-router-dom';

//todo: fix forms
//todo: fix warnings in console
const MovieRate = (props) => {

    const history = useHistory();
    const [rating, updateRating] = React.useState(0)

    const handleChange = (e) => {
        updateRating({
            ...rating,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const finalRating = rating.rating;

        props.onRateMovie(props.selectedMovie.id.id, finalRating);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label htmlFor="rating">Rating</label>
                        <input type="number"
                               className="form-control"
                               id="rating"
                               name="rating"
                               placeholder="Enter rating"
                               required
                               onChange={handleChange}
                        />
                    </div>


                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>

                    </form>
            </div>

        </div>
    )
}

export default MovieRate;
