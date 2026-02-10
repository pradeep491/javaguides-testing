package com.test.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectClasses({ClassATest.class, ClassBTest.class, ClassCTest.class})
@SelectPackages({"com.test.annotations","com.test.suite"})
public class TestSuite {

}
