package ru.basejava.webapp.storage;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages("ru.basejava.webapp.storage")
@ExcludePackages("ru.basejava.webapp.storage.AbstractStorageTest")
@SuiteDisplayName("Storage Test Suite")

public class JUnit5TestSuite {

}
