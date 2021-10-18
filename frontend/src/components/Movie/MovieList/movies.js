import './movies.css';
import React from "react";
import {Link} from 'react-router-dom';
import MoviesTerm from '../MovieTerm/movieTerm'
import ReactPaginate from 'react-paginate'

class MoviesList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 3,
            dynamicMoviePosterUrl: ""
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.movies.length / this.state.size);
        const movies = this.getMoviesPage(offset, nextPageOffset);

        return (
            <div className={"container mm-4"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <div className={"row"}>
                            <div className={"col-md-8 mt-5 mr-5"}>

                                <div className="row">
                                    <div className={"col-md-8"}>
                                        <h1 className={"text-danger"}>
                                            Current Movies
                                        </h1>
                                    </div>
                                    <div className="col-sm-4 col-md-4 buttonDiv">
                                        <Link className={"btn btn-lg btn-block btn-dark"} to={"/movie/add"}>Add
                                            Movie</Link>
                                    </div>
                                </div>
                                <hr/>
                                <table className={"table"}>
                                    <thead>
                                    </thead>
                                    <tbody>
                                    {movies}
                                    </tbody>

                                </table>


                                <ReactPaginate previousLabel={"back"}
                                               nextLabel={"next"}
                                               breakLabel={<a className={"page-link"} href="/#">...</a>}
                                               breakClassName={'page-item'}
                                               breakLinkClassName={'page-link'}
                                               containerClassName={'pagination m-4 justify-content-center'}
                                               pageClassName={'page-item'}
                                               pageLinkClassName={'page-link'}
                                               previousClassName={'page-item'}
                                               previousLinkClassName={'page-link'}
                                               nextClassName={'page-item'}
                                               nextLinkClassName={'page-link'}
                                               activeClassName={'active'}
                                               pageCount={pageCount}
                                               marginPagesDisplayed={3}
                                               pageRangeDisplayed={5}
                                               onPageChange={this.handlePageClick}/>

                            </div>
                            <div className={"col-md-1"}></div>
                            <div className={"col-md-3 ml-2 imgDiv mb-0"}>
                                {/*fix if empty*/}
                                <img id={"imgImg"} className={"imgImg"} height={"1100px"} src={""}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    componentDidMount () {
        if(this.props.movies.length > 0) {
            this.myInterval = setInterval(() => {
                let index = Math.floor(Math.random() * (this.props.movies.length));
                document.getElementById("imgImg").src = this.props.movies[index].url;
            }, 2000);
        }
    }

    componentWillUnmount() {
        clearInterval(this.myInterval);
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getMoviesPage = (offset, nextPageOffset) => {
        return this.props.movies.map((term, index) => {
            return (
                <MoviesTerm term={term}
                            onDetails={this.props.onDetails}
                            onFetchScheduledMoviesByMovieId={this.props.onFetchScheduledMoviesByMovieId}
                            onScheduleMovie={this.props.onScheduleMovie}
                />
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

export default MoviesList;