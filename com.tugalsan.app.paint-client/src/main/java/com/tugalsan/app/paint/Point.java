/*
 * Copyright 2010 Hydro4GE, Incorporated. http://www.hydro4ge.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tugalsan.app.paint;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * point object returned by Path.getPointAtLength()
 */
public class Point extends JavaScriptObject {

  protected Point() {}

  public final native double x() /*-{
    if (this.x == undefined)
      return -1;
    else
      return this.x;
  }-*/;

  public final native double y() /*-{
    if (this.y == undefined)
      return -1;
    else
      return this.y;
  }-*/;

  public final native double alpha() /*-{
    if (this.alpha == undefined)
      return -1;
    else
      return this.alpha;
  }-*/;

}

