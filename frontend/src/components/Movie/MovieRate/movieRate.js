import React from 'react';
import {useHistory} from 'react-router-dom';

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

        props.onRateMovie(props?.selectedMovie?.id?.id, finalRating);
        history.push("/movie");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-4">
                <form onSubmit={onFormSubmit}>

                    <div className="form-group">
                        <label htmlFor="rating">Rating</label>
                        <input type="number"
                               className="form-control"
                               id="rating"
                               name="rating"
                               min={"0"}
                               max={"10"}
                               placeholder="Enter rating"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <br/>


                    <div className={"text-center"}>
                    <button id="submit" type="submit" className="btn btn-dark align-self-md-center">Submit</button>
                    </div>
                    </form>
            </div>
            <div className={"col-md-2"}> </div>
            <div className={"col-md-6"}>
               <h1> We value your feedback! <img height={"30px"} src={"https://www.iconpacks.net/icons/2/free-feedback-icon-2949-thumb.png"}/></h1>

            </div>

        </div>
    )
}

export default MovieRate;
