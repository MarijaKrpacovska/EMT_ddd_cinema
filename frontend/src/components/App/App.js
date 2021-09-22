import './App.css';
import React, {Component} from "react";
import Movies from '../Movie/movie';
import MovieService from "../../repository/movieRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            movies: []
        }
    }

    render() {
        return (
                    <div className="container">
                            <Movies movies={this.state.movies}/>
                    </div>
        );
    }

    componentDidMount() {
        this.loadMovies();
    }

    loadMovies = () => {
        MovieService.fetchMovies()
            .then((data) => {
                this.setState({
                    movies: data.data
                })
            });
    }
}

export default App;
