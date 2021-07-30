import React from 'react'
import Footer from './Footer'
import {shallow  ,configure } from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';

configure({ adapter: new Adapter() });

test("renders footer",()=>{
    const wrapper = shallow(<Footer />);
    expect(wrapper.exists()).toBe(true);
});

test("includes 4 icons", () => {
    const wrapper = shallow(<Footer />);
    expect(wrapper.find("FontAwesomeIcon").length).toEqual(4);
});
