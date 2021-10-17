import './footer.css'
import React from 'react';
import {Link} from 'react-router-dom';

const footer = (props) => {
    return (
        <footer className="footer mt-3">
            <div className={"bg-dark text-light pt-4 pb-4"}>
                <div className={"container"}>
                    {/*<div className={"row rowClass"}>*/}
                    {/*    <div className={"col"}>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*    </div>*/}
                    {/*    <div className={"col"}>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*    </div>*/}
                    {/*    <div className={"col"}>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*        <p>bla</p>*/}
                    {/*    </div>*/}
                    {/*</div>*/}
                    {/*<hr/>*/}
                    <div className={"row font-weight-bold"}>
                        <div className={"col"}>

                                FOLLOW US:
                                {/*<i className="fa fa-facebook p-2 pt-0 pb-0 text-primary"></i>*/}
                               <img className={"facebookImg"} src={"https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Facebook_icon_2013.svg/1024px-Facebook_icon_2013.svg.png"} height={"14px"} width={"14px"}/>
                                <i className="fa fa-twitter p-2 pt-0 pb-0 text-info"></i>
                                {/*<i className="fa fa-instagram p-2 pt-0 pb-0"></i>*/}
                            {/*<img className={"facebookImg"} src={"https://www.jaspersomsen.com/wp-spullies/uploads/2017/03/instagram-Logo-PNG-Transparent-Background-download.png"} height={"18px"} width={"18px"}/>*/}
                        </div>

                        <div className={"col secondCol"}>
                                <small className={"contact m-lg-1"}>
                                Contact
                                </small>
                                <small className={"aboutUs m-lg-4"}>
                                About us
                                </small>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    )
}

export default footer;
