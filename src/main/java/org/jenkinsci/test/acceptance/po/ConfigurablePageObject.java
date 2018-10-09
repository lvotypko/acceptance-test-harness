/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.test.acceptance.po;

import java.net.URL;
import java.util.concurrent.Callable;

import com.google.inject.Injector;

import groovy.lang.Closure;
import org.openqa.selenium.By;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.jenkinsci.test.acceptance.Matchers.*;

/**
 * {@link PageObject} that can be configured and saved.
 * <p>
 * There are 2 uses of this:
 * - {@link ContainerPageObject}, PO that has /configure page associated
 * - PO that is itself a config page, such as /configureSecurity
 *
 * @author ogondza.
 */
public abstract class ConfigurablePageObject extends PageObject {
    protected ConfigurablePageObject(PageObject context, URL url) {
        super(context, url);
    }

    public ConfigurablePageObject(Injector injector, URL url) {
        super(injector, url);
    }

    /**
     * Edits this configurable page object using the specified configuration lambda. Opens the configuration view,
     * runs the specified body and saves the changes.
     *
     * @param body the additional configuration options for this page object
     */
    public void configure(final Runnable body) {
        System.err.println("go to config page");
        configure();
        System.err.println("on config page");
        body.run();
        save();
    }

    /**
     * Edits this configurable page object using the specified closure. Opens the configuration view,
     * runs the specified body and saves the changes.
     *
     * @param body the additional configuration options for this page object
     */
    public void configure(final Closure body) {
        configure();
        body.call(this);
        save();
    }

    /**
     * Edits this configurable page object using the specified callable. Opens the configuration view,
     * runs the specified body and saves the changes.
     *
     * @param body the additional configuration options for this page object
     * @return return value of the body
     */
    public <T> T configure(final Callable<T> body) {
        try {
            configure();
            T v = body.call();
            save();
            return v;
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Open configuration page if not yet opened.
     *
     * @see #getConfigUrl()
     */
    public void configure() {
        System.err.println(driver.getCurrentUrl() + " " + getConfigUrl().toExternalForm());
        if (driver.getCurrentUrl().equals(getConfigUrl().toExternalForm())) {
            System.err.println("is equals " + getClass().getName());
            return;
        }
        System.err.println("go to configure url" + getClass().getName());
        visit(getConfigUrl());
        System.err.println("on configure url " + getClass().getName());
        waitFor(By.xpath("//form[contains(@name, '" + getFormName() + "')]"), 10);
        waitFor(By.xpath("//span[contains(@class, 'submit-button')]//button[contains(text(), '" + getSubmitButtonText() + "')]"), 5);
    }

    public String getFormName(){
        return "config";
    }

    public String getSubmitButtonText(){
        return "Save";
    }

    /**
     * Makes sure that the browser is currently opening the configuration page.
     */
    public void ensureConfigPage() {
        assertThat("config page is open", driver.getCurrentUrl(), is(getConfigUrl().toExternalForm()));
    }

    public abstract URL getConfigUrl();

    public void save() {
        clickButton("Save");
        assertThat(driver, not(hasContent("This page expects a form submission")));
    }

    public void apply() {
        clickButton("Apply");
        waitFor(driver, hasContent("Saved"), 30);
    }
}
